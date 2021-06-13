<template>
  <div>
    <pharmacy-admin-home>
    </pharmacy-admin-home>
    <v-row justify="center">
      <v-col
        cols="12"
        sm="10"
        md="8"
        lg="6"
      >
        <v-card class="overflow-hidden">
          <v-app-bar 
            color="dark cyan"
            dark
            prominent
            flat>
            <v-toolbar-title>Discount definition</v-toolbar-title>
          </v-app-bar>
          <v-spacer></v-spacer>
          <v-container>
            <div id="pickers">
              <v-row justify="center">
                <div id="start-date-pickr">
                  <v-date-picker v-model="startDate"
                  color="dark cyan"></v-date-picker>
                </div>
                <div id="end-date-pickr">
                  <v-date-picker v-model="endDate"
                  color="dark cyan"></v-date-picker>
                </div>
              </v-row>
            </div>
            <br />
            <hr />
            <br />
            <div id="discount">
              <v-subheader>Discount: {{percentage}}%</v-subheader>
              <v-slider
                v-model="percentage"
                step="5"
                thumb-label
                ticks
              ></v-slider>
            </div>
            <v-btn class="submit-dgm" @click="setDiscount">Submit</v-btn>
          </v-container>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import axios from 'axios'
import PharmacyAdminHome from './PharmacyAdminHome.vue'
export default {
    components: { PharmacyAdminHome },
    name: 'MedicineDiscount',
    data () {
        return {
            startDate: '',
            endDate: '',
            percentage: 0
        }
    },
    methods: {
        setDiscount() {
            if (this.startDate > this.endDate)
                alert('End date must be after start date')
            else {
                alert('Item will have discount ' + this.percentage + '% from ' 
                    + this.startDate + ' until ' + this.endDate)
                
                let discount = {
                    startDate: this.startDate,
                    endDate: this.endDate,
                    percentage: this.percentage,
                    pharmacyRegNo: this.$route.params.regNo,
                    medicineCode: this.$route.params.code
                }

                axios
                .post('http://localhost:9090/api/discount/new', discount)
                .then(response => {
                    console.log(response)
                    alert('Succesfully created a discount')
                    this.$router.push('/pharmacyadmin')
                })
                .catch(error => {
                    
                    alert(error.response.data)
                    console.log(error.response.data)
                })
            }
        }
    }
}
</script>

<style>
 #start-date-pickr {
   margin: 15px;
   display: inline;
 }

 #end-date-pickr {
   display: inline;
   margin: 15px;
 }

 #discount {
     width: 50%;
     padding: 20px;
     margin: 15px;
 }

 .submit-dgm {
   margin: 10px;
 }
</style>