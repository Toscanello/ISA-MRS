<template>
  <v-container >
    <v-row>
      <v-col>
        <v-text-field label="Name" v-model="patientName" filled></v-text-field>
      </v-col>
      <v-col>
        <v-text-field label="Surname" v-model="surname" filled></v-text-field>
      </v-col>
      <v-col>
        <button type="button" v-on:click="search()">Search</button>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "PatientSearch",
  data: () => ({
    patientName: null,
    surname: null,
  }),
  methods: {
    search: function () {
      this.$emit("clicked", { name: this.patientName, surname: this.surname });
    },
  },
};
</script>
<style scoped>
.col{
  padding: 0px;
  padding-top: 27px;
  padding-right: 5px;
}
button{
  margin-top: 15px;
  margin-right: 100px;
}
</style>
