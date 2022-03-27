<template>
  <div>
    <v-btn @click="addNewMovie" class="my-4 mx-4" color="success" rounded>
      <v-icon left> mdi-pencil</v-icon>
      Add new Movie
    </v-btn>

    <ul>
      <li class="box" v-for="movie in renderMovies" :key="movie.id">
        <p>Genre: {{ movie.genre }}</p>
        <p>Movie name: {{ movie.name }}</p>
        <p>Release year: {{ movie.releaseYear }}</p>
        <p>Directed by: {{ movie.director.name }}</p>

        <v-btn @click="updateMovie(movie.id)" color="info">Update</v-btn>
        <v-btn @click="deleteMovie(movie.id)" color="error" class="mx-4">
          X
        </v-btn>
      </li>
    </ul>
  </div>
</template>

<style>
.box {
  padding: 100px 0;
  width: 400px;
  text-align: center;
  background-color: #ddd;
  margin: 20px;
  display: inline-block;
}
</style>

<script>
import axios from "axios";

export default {
  name: "Home",
  data() {
    return {
      bctx: "http://localhost:8080",
      movies: [],
    };
  },
  // this is when the component is added to the DOM
  mounted() {
    axios
        .get(this.bctx + "/api/movies")
        .then((response) => {
          for (let i = 0; i < response.data.length; i++) {
            this.movies.push(response.data[i]);
          }
        })
        .catch((error) => console.log(error));
  },
  // basic methods
  methods: {
    addNewMovie: function () {
      this.$router.push({name: "addMovie"});
    },
    deleteMovie: function (movieId) {
      axios
          .delete(this.bctx + "/api/movies/" + movieId)
          .then((response) => {
            alert("Selected movie was deleted");
            this.$router.go();
          })
          .catch((error) => alert(error));
    },
    updateMovie(movieId) {
      axios.get(this.bctx + "/api/movies/" + movieId).then((response) => {
        this.$router.push({
          name: "addMovie",
          params: {movie: response.data},
        });
      });
    },
  },
  // used for rendering different components (used especially with v-for)
  computed: {
    renderMovies: function () {
      return this.movies;
    },
  },
};
</script>
