const mongoose = require("mongoose");

const LocationSchema = new mongoose.Schema(
  {
    Place_Id: {
      type: Number,
      default: 0,
    },
    Place_Name: {
      type: String,
      required: true,
    },
    Description: {
      type: String,
      required: true,
    },
    Category: {
      type: String,
      required: true,
    },
    City: {
      type: String,
      required: true,
    },
    Price: {
      type: Number,
      required: true,
    },
    Rating: {
      type: Number,
      required: true,
    },
    Lat: {
      type: Number,
      required: true,
    },
    Long: {
      type: Number,
      required: true,
    },
    Image: {
      type: String,
      default: "",
    },
  },
  { timestamps: true }
);

const Location = mongoose.model("locations", LocationSchema);

module.exports = Location;
