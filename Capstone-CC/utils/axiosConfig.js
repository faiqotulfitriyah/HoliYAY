const axios = require("axios");

const instance = axios.create({
  baseURL: "https://machinelearning-2esnppdf4a-et.a.run.app/",
  //   timeout: 1000,
});

module.exports = instance;
