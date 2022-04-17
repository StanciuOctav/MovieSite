import axios from "axios";

export default class MovieModel {
    constructor(id, name, genre, releaseYear, director, imageURL) {
        this.id = id
        this.name = name
        this.genre = genre
        this.releaseYear = releaseYear
        this.director = director
        this.imageURL = imageURL
    }

    createMovieModel() {
        return axios
            .post(process.env.VUE_APP_SERVER_URL + "/api/movies", this)
    }

    updateMovieModel() {
        return axios
            .patch(
                process.env.VUE_APP_SERVER_URL + "/api/movies/" + this.id,
                this
            )
    }

    setImage(imageURL) {
        this.imageURL = imageURL
    }

    setId(id) {
        this.id = id
    }

    setName(name) {
        this.name = name
    }

    setGenre(genre) {
        this.genre = genre
    }

    setReleaseYear(releaseYear) {
        this.releaseYear = releaseYear
    }

    setDirector(director) {
        this.director = director
    }
}