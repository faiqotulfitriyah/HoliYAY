"use strict";
const userModel = require("../models/user");

class UserController {
  static async register(req, res, next) {
    console.log(req.body);
    const user = new userModel(req.body); //membuat object user berdasarkan skema userModel dengan data yang ada pada req.body

    try {
      await user.save(); //untuk save data ke dalam database

      res.status(200).json({user});
    } catch (error) {
      res.status(500).json({message: "Internal server error"});
    }
  }

  static async login(req, res, next) {
    try {
      const { username, password } = req.body;
      const findUser = await userModel.findOne({ username: username }).exec(); //mencari username di database berdasarkan username
      if (findUser) {
        if (findUser.password == password) {
          // res.send("Login berhasil");
          res.status(200).json({message: "Login berhasil"});
        } else {
          // res.send("Password salah");
          res.status(400).json({message: "Password salah"});
        }
      } else {
        // res.send("Username tidak ditemukan");
        res.status(400).json({ message: "Username tidak ditemukan" });
      }
    } catch (error) {
      res.status(500).json({message: "Internal server error"});
    }
  }
}

module.exports = UserController;