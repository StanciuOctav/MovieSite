<template>
  <div>
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
      movies: [],
    };
  },
  // this is when the component is added to the DOM
  mounted() {
    axios
        .get(process.env.VUE_APP_SERVER_URL + "/api/movies")
        .then((response) => {
          for (let i = 0; i < response.data.length; i++) {
            this.movies.push(response.data[i]);
          }
        })
        .catch((error) => console.log(error));
  },
  // basic methods
  methods: {
    deleteMovie: function (movieId) {
      axios
          .delete(process.env.VUE_APP_SERVER_URL + "/api/movies/" + movieId)
          .then(() => {
            alert("Selected movie was deleted");
            this.$router.go();
          })
          .catch((error) => alert(error));
    },
    updateMovie(movieId) {
      axios
          .get(process.env.VUE_APP_SERVER_URL + "/api/movies/" + movieId)
          .then((response) => {
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
