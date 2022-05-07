<template>
  <div style="background-color: #827397">
    <ul>
      <li class="box" v-for="movie in renderMovies" :key="movie.id">
        <div class="gallery">
          <img :src="movie.imageURL" @click="showMovieDetails(movie.id)"/>
          <h3>{{ movie.name }}</h3>
          <v-btn @click="updateMovie(movie.id)" color="info">Update</v-btn>
          <v-btn @click="deleteMovie(movie.id)" color="error" class="mx-4"
          >X
          </v-btn>
        </div>
      </li>
    </ul>
  </div>
</template>

<style>
ul li {
  display: inline-table;
}

div.gallery {
  margin: 30px;
  width: 300px;
  display: inline-table;
  text-align: center;
}

div.gallery:hover {
  border: 1px solid rgb(0, 0, 0);
}

img {
  width: 100%;
  height: auto;
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
    showMovieDetails(movieId) {
      axios
          .get(process.env.VUE_APP_SERVER_URL + "/api/movies/" + movieId)
          .then((response) => {
            this.$router.push({
              name: "movieDetails",
              params: {id: response.data.id},
            });
          });
    },
    deleteMovie(movieId) {
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
