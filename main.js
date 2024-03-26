// How should I name my variables?   

// Bad
let d
let elapsed
const ages = arr.map((i) => i.age)

// God
let daysSinceModification
const agesOfUsers = users.map((user) => user.age)

// DON'T
let nameString
let theUsers

// DO
let name
let users


// Please use constants & names easy to pronounce

// DON'T
let fName, lName
let cntr

let full = false
if (cart.size > 100) {
    full = true
}

// DO
let firstName, lastName
let counter

const MAX_CART_SIZE = 100
// ...
const isFull = cart.size > MAX_CART_SIZE

// How should I write my functions?
// Functions should do one thing. They should do it well. They should do it only. — Robert C. Martin (Uncle Bob)

// DON'T
function getUserRouteHandler(req, res) {
    const { userId } = req.params
    // inline SQL query
    knex('user')
        .where({ id: userId })
        .first()
        .then((user) => res.json(user))
}

// DO
// User model (eg. models/user.js)
const tableName = 'user'
const User = {
    getOne(userId) {
        return knex(tableName)
            .where({ id: userId })
            .first()
    }
}

// route handler (eg. server/routes/user/get.js)
function getUserRouteHandler(req, res) {
    const { userId } = req.params
    User.getOne(userId)
        .then((user) => res.json(user))
}


// Use long, descriptive names

// DON'T
/**
 * Invite a new user with its email address
 * @param {String} user email address
 */

function inv(user) { /* implementation */ }

// DO
function inviteUser(emailAddress) { /* implementation */ }


// Avoid long argument list
// Use a single object parameter and destructuring assignment instead. 
// It also makes handling optional parameters much easier.


// DON'T
function getRegisteredUsers(fields, include, fromDate, toDate) { /* implementation */ }
getRegisteredUsers(['firstName', 'lastName', 'email'], ['invitedUsers'], '2016-09-26', '2016-12-13')

// DO
function getRegisteredUsers({ fields, include, fromDate, toDate }) { /* implementation */ }
getRegisteredUsers({
    fields: ['firstName', 'lastName', 'email'],
    include: ['invitedUsers'],
    fromDate: '2016-09-26',
    toDate: '2016-12-13'
})


// Reduce side effects
// Use pure functions without side effects, whenever you can. 
// They are really easy to use and test.

// DON'T
function addItemToCart(cart, item, quantity = 1) {
    const alreadyInCart = cart.get(item.id) || 0
    cart.set(item.id, alreadyInCart + quantity)
    return cart
}

// DO
// not modifying the original cart
function addItemToCart(cart, item, quantity = 1) {
    const cartCopy = new Map(cart)
    const alreadyInCart = cartCopy.get(item.id) || 0
    cartCopy.set(item.id, alreadyInCart + quantity)
    return cartCopy
}

// or by invert the method location
// you can expect that the original object will be mutated
// addItemToCart(cart, item, quantity) -> cart.addItem(item, quantity)
const cart = new Map()
Object.assign(cart, {
    addItem(item, quantity = 1) {
        const alreadyInCart = this.get(item.id) || 0
        this.set(item.id, alreadyInCart + quantity)
        return this
    }
})


// Organize your functions in a file according to the stepdown rule
// Higher level functions should be on top and lower levels below. 
// It makes it natural to read the source code.


// DON'T
// "I need the full name for something..."
function getFullName(user) {
    return `${user.firstName} ${user.lastName}`
}

function renderEmailTemplate(user) {
    // "oh, here"
    const fullName = getFullName(user)
    return `Dear ${fullName}, ...`
}

// DO
function renderEmailTemplate(user) {
    // "I need the full name of the user"
    const fullName = getFullName(user)
    return `Dear ${fullName}, ...`
}

// "I use this for the email template rendering"
function getFullName(user) {
    return `${user.firstName} ${user.lastName}`
}


// How to write nice async code?

// DON'T
asyncFunc1((err, result1) => {
    asyncFunc2(result1, (err, result2) => {
        asyncFunc3(result2, (err, result3) => {
            console.lor(result3)
        })
    })
})

// DO
asyncFuncPromise1()
    .then(asyncFuncPromise2)
    .then(asyncFuncPromise3)
    .then((result) => console.log(result))
    .catch((err) => console.error(err))




// Single concept per test 

describe("MomentJS", () => {
    it("handles date boundaries", () => {
        let date

        date = new MomentJS("1/1/2015")
        date.addDays(30)
        assert.equal("1/31/2015", date)

        date = new MomentJS("2/1/2016")
        date.addDays(28)
        assert.equal("02/29/2016", date)
    })
})

describe("MomentJS", () => {
    it("handles 30-day months", () => {
        const date = new MomentJS("1/1/2015")
        date.addDays(30)
        assert.equal("1/31/2015", date)
    })

    it("handles leap year", () => {
        const date = new MomentJS("2/1/2016")
        date.addDays(28)
        assert.equal("02/29/2016", date)
    })
})

