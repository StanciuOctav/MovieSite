<template>
  <div>
    <ul>
      <li id="first">
        <img :src="actor.imageURL"/>
      </li>

      <li id="second">
        <h1>{{ actor.name }}</h1>
        <br/>
        <p><b>Age:</b> {{ actor.age }}</p>
        <p><b>Born in:</b> {{ actor.bornIn }}</p>
        <p><b>Networth:</b> {{ actor.netWorth }}</p>
      </li>
    </ul>

    <div style="margin-top: 300px">
      <h2 style="margin-left: 50px">Movies that {{ actor.name }} acted in:</h2>
      <ul>
        <li v-for="m in actedInMovies" :key="m.id">
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
  name: "actorDetails",
  data() {
    return {
      actor: null,
      actedInMovies: [],
    };
  },
  created() {
    let URL1 =
        process.env.VUE_APP_SERVER_URL + "/api/actors/" + this.$route.params.id;
    let URL2 =
        process.env.VUE_APP_SERVER_URL +
        "/api/movies/moviesActedIn/" +
        this.$route.params.id;

    const promise1 = axios.get(URL1);
    const promise2 = axios.get(URL2);

    Promise.all([promise1, promise2]).then((values) => {
      this.actor = values[0].data;
      this.actedInMovies = values[1].data;
    });
  },
  methods: {
    redirectToMovie(movieId) {
      this.$router.push({
        name: "movieDetails",
        params: {id: movieId},
      });
    },
  },
};
</script>
