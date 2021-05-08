<template>
  <div>
    <v-row class="head" justify="center" style="margin-top:5px;">
      Vaccation form
    </v-row>
    <v-row justify="center">
      <v-date-picker
        v-model="date"
        width="800"
        class="mt-4"
        range
        :min="new Date().toISOString().slice(0, 10)"
        elevation="15"
      ></v-date-picker>
    </v-row>
    <v-row justify="center"> 
        <v-btn>
            Submit
        </v-btn>
    </v-row>
  </div>
</template>

<script>
export default {
  name: "Vaccation",
  data() {
    return {
      date: [],
    };
  },
};
</script>

<style scoped>
.row {
  margin-top: 50px;
}
.head{
  font: bold;
  color: rgb(0, 140, 255);
  font-size: 50px;
}
</style>