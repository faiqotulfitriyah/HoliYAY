"use strict";

const router = require("express").Router();
const LocationController = require("../controllers/locationController");
const upload = require("../utils/cloudStorage");

router.post("/", upload.single("Image"), LocationController.create);
router.get("/", LocationController.findAll);
router.get("/:id", LocationController.findOne);
router.put("/:id", upload.single("Image"), LocationController.edit);
router.post("/recommendation", LocationController.recommendation);

module.exports = router;
