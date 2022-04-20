import axios from "axios";

export default class MovieModel {
    constructor(id, name, genre, releaseYear, director, imageURL, description, actors) {
        this._id = id;
        this._name = name;
        this._genre = genre;
        this._releaseYear = releaseYear;
        this._director = director;
        this._imageURL = imageURL;
        this._description = description;
        this._actors = actors;
    }

    createMovieModel() {
        return axios
            .post(process.env.VUE_APP_SERVER_URL + "/api/movies", this)
    }

    updateMovieModel() {
        return axios
            .patch(
                process.env.VUE_APP_SERVER_URL + "/api/movies/" + this._id,
                this
            )
    }

    get actors() {
        return this._actors;
    }

    set actors(actors) {
        this._actors = actors;
    }


    get id() {
        return this._id;
    }

    set id(value) {
        this._id = value;
    }

    get name() {
        return this._name;
    }

    set name(value) {
        this._name = value;
    }

    get genre() {
        return this._genre;
    }

    set genre(value) {
        this._genre = value;
    }

    get releaseYear() {
        return this._releaseYear;
    }

    set releaseYear(value) {
        this._releaseYear = value;
    }

    get director() {
        return this._director;
    }

    set director(value) {
        this._director = value;
    }

    get imageURL() {
        return this._imageURL;
    }

    set imageURL(value) {
        this._imageURL = value;
    }

    get description() {
        return this._description;
    }

    set description(value) {
        this._description = value;
    }
}