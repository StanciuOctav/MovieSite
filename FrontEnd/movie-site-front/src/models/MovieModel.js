import axios from "axios";

export default class MovieModel {
    constructor(id, name, genre, releaseYear, imageURL, director, description) {
        this._id = id;
        this._name = name;
        this._genre = genre;
        this._releaseYear = releaseYear;
        this._imageURL = imageURL;
        this._description = description;
        this._director = director;
    }

    createMovieModel() {
        return axios
            .post(process.env.VUE_APP_SERVER_URL + "/api/movies", {
                name: this._name,
                genre: this._genre,
                releaseYear: this._releaseYear,
                imageURL: this._imageURL,
                description: this._description,
                director: this._director
            })
    }

    updateMovieModel() {
        return axios
            .patch(
                process.env.VUE_APP_SERVER_URL + "/api/movies/" + this._id,
                {
                    name: this._name,
                    genre: this._genre,
                    releaseYear: this._releaseYear,
                    imageURL: this._imageURL,
                    description: this._description,
                    director: this._director
                }
            )
    }

    addMovieReview(id, email, content) {
        const url = `${process.env.VUE_APP_SERVER_URL}/api/review/${id}/${email}/${content}`;
        return axios.post(url);
    }

    deleteMovieReview(id, email) {
        const url = `${process.env.VUE_APP_SERVER_URL}/api/review/${id}/${email}`;
        return axios.delete(url)
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