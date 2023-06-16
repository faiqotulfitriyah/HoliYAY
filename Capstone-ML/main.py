from flask import Flask, jsonify, request, Response
from flask_cors import CORS
import machine_learning


app = Flask(__name__)
CORS(app)


@app.route('/machine-learning', methods=['POST'])
def findAll():

    keywords = request.json["keywords"]
    city = request.json["city"]
    ML = machine_learning.recommend_destinations(keywords, city)
    return jsonify(ML)


if __name__ == '__main__':
    app.run(debug=True)
