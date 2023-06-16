require("dotenv").config();
const express = require("express");
const cors = require("cors");
const connectdb = require("./database");
const Router = require("./routes");
const errorHandler = require("./middlewares/errorHandling");

const port = process.env.PORT || 4000;
const app = express();
app.use(cors());
app.use(express.urlencoded({ extended: true })); //buat parsing url encode
app.use(express.json());

connectdb();

app.use("/", Router);
app.use(errorHandler);
app.listen(port, () => {
  console.log("Server is running at port ", port);
});
