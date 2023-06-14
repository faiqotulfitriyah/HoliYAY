"use strict";

const router = require("express").Router();
const UsersController = require("../controllers/userController");
const upload = require("../utils/cloudStorage");

router.post("/register", UsersController.register);
router.post("/login", UsersController.login);
router.get("/", UsersController.findAll);
router.get("/:id", UsersController.findOne);
router.put("/:id", upload.single("image"), UsersController.edit);
// router.post("/loginGoogle", UsersController.loginGoogle);

module.exports = router;
