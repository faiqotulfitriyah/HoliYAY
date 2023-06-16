"use strict";
const userModel = require("../models/user");
const { generateToken, verifyToken } = require("../utils/jwtHandler");
const {
  passwordEncryption,
  passwordValidation,
} = require("../utils/passwordHandler");

class UserController {
  static async register(req, res, next) {
    const user = new userModel({
      username: req.body.username,
      email: req.body.email,
      password: passwordEncryption(req.body.password),
      location: req.body.location,
    }); //membuat object user berdasarkan skema userModel dengan data yang ada pada req.body
    try {
      await user.save(); //untuk save data ke dalam database
      res.status(201).json({ user, message: "Pendaftaran berhasil!" });
    } catch (error) {
      next(error);
    }
  }

  static async login(req, res, next) {
    try {
      const { username, password } = req.body;
      const findUser = await userModel.findOne({ username: username }).exec(); //mencari username di database berdasarkan username
      if (findUser) {
        if (passwordValidation(password, findUser.password)) {
          res.status(200).json({
            user: {
              username: findUser.username,
              email: findUser.email,
            },
            message: "Login berhasil",
            token: generateToken({
              id: findUser._id,
              username: findUser.username,
              email: findUser.email,
            }),
          });
        } else {
          res.status(400).json({ message: "Password salah" });
        }
      } else {
        res.status(400).json({ message: "Username tidak ditemukan" });
      }
    } catch (error) {
      next(error);
    }
  }

  static async findAll(req, res, next) {
    try {
      const findUsers = await userModel.find();
      res.status(200).json({ users: findUsers });
    } catch (error) {
      next(error);
    }
  }

  static async findOne(req, res, next) {
    try {
      const { id } = req.params;
      const findUser = await userModel.findOne({ _id: id }).exec();
      res.status(200).json({ user: findUser });
    } catch (error) {
      next(error);
    }
  }

  static async edit(req, res, next) {
    try {
      const { id } = req.params;
      const { username, password, email, location, image } = req.body;
      const userId = req.userId;

      if (userId == id) {
        const updateUser = await userModel.findOneAndUpdate(
          { _id: id },
          {
            username,
            password: password && passwordEncryption(password),
            email,
            location,
            image,
          },
          {
            new: true,
            upsert: true,
          }
        );
        res.status(200).json({ user: updateUser });
      } else {
        throw {
          message: "Tidak ada izin akses",
          name: "UnauthorizedError",
        };
      }
    } catch (error) {
      next(error);
    }
  }
}

module.exports = UserController;
