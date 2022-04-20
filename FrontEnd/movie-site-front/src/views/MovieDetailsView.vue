<template>
  <div>
    <ul>
      <li id="first">
        <img :src="movie.imageURL"/>
      </li>

      <li id="second">
        <h1>{{ movie.name }}</h1>
        <br/>
        <p><b>Genre:</b> {{ movie.genre }}</p>
        <p><b>Release Year:</b> {{ movie.releaseYear }}</p>
        <p>
          <b>Director: </b>
          {{ movie.director.name }}
        </p>
        <img
            @click="redirectToDirector(movie.director.id)"
            class="director"
            :src="movie.director.imageURL"
            style="width: 100px; height: 150px"
        />
        <p><b>Description: </b>{{ movie.description }}</p>
      </li>
    </ul>

    <h2>Actors that played in "{{ movie.name }}"</h2>
    <ul>
      <li v-for="m in movie.actedInActors" :key="m.id">
        <div class="gallery">
          <p>{{ m.name }}</p>
          <img :src="m.imageURL" @click="redirectToActor(m.id)"/>
        </div>
      </li>
    </ul>
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
  padding: 20px;
  float: inline-start;
  overflow: hidden;
}
</style>

<script>
import axios from "axios";

export default {
  name: "movieDetails",
  data() {
    return {
      movie: null,
    };
  },
  created() {
    this.movie = this.$route.params.movie;
  },
  methods: {
    redirectToDirector(directorId) {
      console.log(directorId);
    },
    redirectToActor(actorId) {
      console.log(actorId);
    },
  },
};
</script>
