"use strict";

const router = require("express").Router();
const UsersRoute = require("./userRoute");
const LocationsRoute = require("./locationRoute");

// ROUTES
router.use("/users", UsersRoute);
router.use("/locations", LocationsRoute);

module.exports = router;
