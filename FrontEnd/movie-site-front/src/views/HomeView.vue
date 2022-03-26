<template>
  <div>
    <button @click="getAllMovies">
      BUTTON: Populate with movies
    </button>

    <ul>
      <li class="box" v-for="movie in renderMovies" :key="movie.id">
        <p> Genre: {{ movie.genre }} </p>
        <p>Movie name: {{ movie.name }}</p>
        <p>Release year: {{ movie.releaseYear }}</p>
        <p>Directed by: {{ movie.director.name }} </p>
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
import axios from "axios"

export default {
  name: 'Home',
  data() {
    return {
      bctx: "http://localhost:8080",
      fctx: window.location.protocol + "//" + window.location.host,
      movies: []
    }
  },
  methods: {
    getAllMovies: function () {
      axios.get(this.bctx + "/api/movies")
          .then(response => {
            for (let i = 0; i < response.data.length; i++) {
              this.movies.push(response.data[i])
              console.log(response.data[i].name)
            }
          })
          .catch(error => (console.log(error)))
      console.log(this.movies)
    }
  },
  computed: {
    renderMovies: function () {
      return this.movies
    }
  }
}
</script>