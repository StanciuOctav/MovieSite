<template>
  <div>
    <ul>
      <li id="first">
        <img :src="director.imageURL"/>
      </li>

      <li id="second">
        <h1>{{ director.name }}</h1>
        <br/>
        <p><b>Age:</b> {{ director.age }}</p>
        <p><b>Born in:</b> {{ director.bornIn }}</p>
        <p><b>Networth:</b> {{ director.netWorth }}</p>
      </li>
    </ul>

    <div style="margin-top: 210px">
      <h2 style="margin-left: 50px">Directed Movies:</h2>
      <ul>
        <li v-for="m in directedMovies" :key="m.id">
          <div class="gallery">
            <h2>{{ m.name }}</h2>
            <img :src="m.imageURL" @click="redirectToMovie(m.id)"/>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<style>
#first {
  margin: 30px;
  width: 300px;
  display: inline-table;
  text-align: center;
  float: left;
}

#second {
  margin: 30px;
  width: 900px;
  display: inline-table;
  border-radius: 25px;
  border: 2px solid #73ad21;
  padding: 30px;
  float: inline-start;
  overflow: hidden;
}
</style>

<script>
import axios from "axios";

export default {
  name: "directorDetails",
  data() {
    return {
      director: null,
      directedMovies: [],
    };
  },
  created() {
    this.director = this.$route.params.director;
    axios
        .get(
            process.env.VUE_APP_SERVER_URL +
            "/api/movies/directedBy/" +
            this.director.id
        )
        .then((response) => {
          this.directedMovies = response.data;
        });
  },
  methods: {
    redirectToMovie(movieId) {
      axios
          .get(process.env.VUE_APP_SERVER_URL + "/api/movies/" + movieId)
          .then((response) => {
            this.$router.push({
              name: "movieDetails",
              params: {movie: response.data},
            });
          });
    },
  },
  computed: {},
};
</script>
