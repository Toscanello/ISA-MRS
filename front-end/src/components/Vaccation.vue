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
        <v-btn @click="onScheduleClick">
            Submit
        </v-btn>
    </v-row>
  </div>
</template>

<script>
import axios from 'axios'
import authHeader from '../services/auth-header';
export default {
  name: "Vaccation",
  data() {
    return {
      date: [],
    };
  },
  methods:{
    onScheduleClick:function(){
      let vaccation={
        medicalWorkerId: `${localStorage.getItem('korisnik')}@gmail.com`,
        startDate: this.date[0],
        endDate: this.date[1],
        status: 'STANDBY'
      };
      if(this.date.length==2){
        console.log(vaccation);
        axios.post('http://localhost:9090/api/vaccation/addVaccation',vaccation, {headers: authHeader()});
        this.$emit("clicked");
      }
    }
  }
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