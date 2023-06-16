"use strict";
const locationModel = require("../models/location");
const axios = require("../utils/axiosConfig");

class LocationController {
  static async create(req, res, next) {
    const location = new locationModel(req.body); //membuat object location berdasarkan skema locationModel dengan data yang ada pada req.body

    try {
      await location.save(); //untuk save data ke dalam database
      res.status(201).json({ location });
    } catch (error) {
      next(error);
    }
  }

  static async findAll(req, res, next) {
    try {
      const { limit = 10, offset = 0 } = req.query;
      const locations = await locationModel
        .find()
        .limit(limit)
        .skip(offset)
        .exec();
      const count = await locationModel.count();
      const pagination = {
        page: offset ? offset / limit + 1 : 1,
        per_page: limit * 1,
        total_data: count,
      };
      res.status(200).json({ locations, pagination });
    } catch (error) {
      next(error);
    }
  }

  static async findOne(req, res, next) {
    try {
      const { id } = req.params;
      const findLocation = await locationModel.findOne({ _id: id }).exec();
      res.status(200).json({ location: findLocation });
    } catch (error) {
      next(error);
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
        }
      );
      res.status(200).json({ location: updateLocation });
    } catch (error) {
      next(error);
    }
  }

  static async recommendation(req, res, next) {
    try {
      const { keywords, city } = req.body;

      const locationRecommendation = await axios({
        method: "post",
        url: "https://machinelearning-2esnppdf4a-et.a.run.app/machine-learning",
        headers: {
          "Content-Type": "application/json",
        },
        data: { keywords, city },
        json: true,
      });
      if (locationRecommendation) {
        res.status(200).json({ recommendation: locationRecommendation.data });
      }
    } catch (error) {
      next(error);
    }
  }
}

module.exports = LocationController;
