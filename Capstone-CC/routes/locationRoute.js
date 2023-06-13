"use strict";

const router = require("express").Router();
const LocationController = require("../controllers/locationController");
const upload = require("../utils/cloudStorage");

router.post("/create", upload.single("image"), LocationController.create);
router.get("/findAll", LocationController.findAll);
router.get("/findOne/:id", LocationController.findOne);
router.put("/edit/:id", upload.single("image"), LocationController.edit);
router.post("/recommendation", LocationController.recommendation);

module.exports = router;
