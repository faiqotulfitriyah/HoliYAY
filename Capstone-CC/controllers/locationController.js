"use strict";
const locationModel = require("../models/location");
const axios = require("../utils/axiosConfig");

class LocationController {
  static async create(req, res, next) {
    console.log(req.body);
    const location = new locationModel(req.body); //membuat object user berdasarkan skema userModel dengan data yang ada pada req.body

    try {
      await location.save(); //untuk save data ke dalam database

      res.status(201).json({ location });
    } catch (error) {
      res.status(500).json({ message: "Internal server error" });
    }
  }

  static async findAll(req, res, next) {
    try {
      const locations = await locationModel.find();
      res.status(200).json({ locations });
    } catch (error) {
      res.status(500).json({ message: "Internal server error" });
    }
  }

  static async findOne(req, res, next) {
    try {
      const { id } = req.params;
      const findLocation = await locationModel.findOne({ _id: id }).exec();
      res.status(200).json({ location: findLocation });
    } catch (error) {
      res.status(500).json({ message: "Internal server error" });
    }
  }

  static async edit(req, res, next) {
    try {
      const { id } = req.params;
      const {
        Place_Id,
        Place_Name,
        Description,
        Category,
        City,
        Price,
        Rating,
        Lat,
        Long,
        Image,
      } = req.body;
      const updateLocation = await locationModel.findOneAndUpdate(
        { _id: id },
        {
          Place_Id,
          Place_Name,
          Description,
          Category,
          City,
          Price,
          Rating,
          Lat,
          Long,
          Image,
        },
        {
          new: true,
          upsert: true,
          rawResult: true,
        }
      );
      res.status(200).json({ location: updateLocation });
    } catch (error) {
      console.log(error);
      res.status(500).json({ message: "Internal server error" });
    }
  }

  static async recommendation(req, res, next) {
    try {
      const { keywords, city = "Jakarta" } = req.body;
      const locationRecommendation = await axios.post("/machine-learning", {
        data: { keywords, city },
      });
      console.log(keywords, city, locationRecommendation);
      res.status(200).json({ locationRecommendation });
    } catch (error) {
      res.status(500).json({ message: "Internal server error" });
    }
  }
}

module.exports = LocationController;
