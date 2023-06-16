module.exports = async (req, res, next) => {
  const { verifyToken } = require("../utils/jwtHandler");
  const userModel = require("../models/user");
  try {
    if (!req.headers.authorization) {
      next({ name: "UnauthorizedError", message: "Silahkan login dulu" });
    }
    const token = req.headers.authorization.replace("Bearer ", "");
    const userToken = verifyToken(token);
    const findUser = await userModel.findOne({ _id: userToken.id }).exec();
    if (userToken.id == findUser._id) {
      next();
    }
  } catch (error) {
    next(error);
  }
};
