<template>
  <div>
    <ul>
      <li id="second">
        <h1>{{ user.name }}</h1>
        <br/>
        <p><b>Email:</b> {{ user.email }}</p>
        <p><b>Age:</b> {{ user.age }}</p>
      </li>
    </ul>

    <div style="margin-top: 20px">
      <h2 style="margin-left: 50px">Movies reviewed:</h2>
      <ul>
        <li v-for="m in reviewedMovies" :key="m.id">
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
#second {
  margin: 0px;
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
  name: "userDetails",
  data() {
    return {
      user: null,
      reviewedMovies: [],
    };
  },
  created() {
    this.user = this.$route.params.user;
    axios
        .get(
            process.env.VUE_APP_SERVER_URL +
            "/api/movies/reviewed/" +
            this.user.email
        )
        .then((response) => {
          this.reviewedMovies = response.data;
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
};
</script>
