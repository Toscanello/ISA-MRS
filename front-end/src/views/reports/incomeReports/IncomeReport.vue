<template>
  <div>
      <pharmacy-admin-home></pharmacy-admin-home>
      <div class="container">

        <v-row align=center>

          <v-col class="d-flex" cols=8 sm=6>
            <v-select
              :items="modes"
              v-model="mode"
              label="Modes"
            ></v-select>
          </v-col>

          <v-col class="d-flex" cols=8 sm=6>
            <v-select
              :items="types"
              v-model="type"
              label="Report types"
            ></v-select>
          </v-col>

          <v-col class="d-flex" cols=4 sm=4>
            <v-btn depressed @click="loadReport">Load report</v-btn>
          </v-col>

        </v-row>
        
        <div class="small">
          <income-report-chart
              v-if="loaded"
              :chartdata="chartData"
              :options="options" />
        </div>
      </div>
  </div>
</template>

<script>
import axios from 'axios'
import incomeReportChart from './IncomeReportChart.vue'
import PharmacyAdminHome from '../../PharmacyAdminHome.vue'
export default {
  components: { incomeReportChart, PharmacyAdminHome },
  name: 'IncomeReport',
  data() {
    return {
      loaded: false,
      chartData: null,
      mode: 'annual',
      modes: ['annual', 'quartal', 'monthly'],
      type: 'income',
      types: ['income', 'appointments', 'medicine']
    }
  },
  computed: {
    regNo() {
      return this.$route.params.regNo
    }
  },
  async mounted() {
    this.loaded = false,
    await axios
    .get('http://localhost:9090/api/pharmacy/report/income/' + this.regNo + '/annual')
    .then(response => {
      let unorderedResponse = response.data
      let orderedRespone = Object.keys(unorderedResponse)
                                 .sort()
                                 .reduce((obj, key) => {
                                   obj[key] = unorderedResponse[key]
                                   return obj
                                 }, {})
      let labels = Object.keys(orderedRespone)
      let data = Object.values(orderedRespone)
      this.chartData = {
        labels: labels,
        datasets: [{
          label: 'Income amount',
          backgroundColor: '#3457D5',
          data: data
        }]
      }
      this.loaded = true
    })
    .catch(error => {
      alert(error.response.data)
    })
  },
  methods: {
    async loadReport() {
      this.loaded = false;

      let dataLabel = ''
      if (this.type === 'appointments')
          dataLabel = 'Appointment number'
      else if (this.type === 'medicine')
          dataLabel = 'Medicine orders'
      else
          dataLabel = 'Income amount'

      await axios
      .get('http://localhost:9090/api/pharmacy/report/' + this.type + '/' + this.regNo + '/' + this.mode)
      .then(response => {
        let unorderedResponse = response.data
        let orderedRespone = Object.keys(unorderedResponse)
                                  .sort()
                                  .reduce((obj, key) => {
                                    obj[key] = unorderedResponse[key]
                                    return obj
                                  }, {})
        let labels = Object.keys(orderedRespone)
        let data = Object.values(orderedRespone)
        this.chartData = {
          labels: labels,
          datasets: [{
            label: dataLabel,
            backgroundColor: '#3457D5',
            data: data
          }]
        }
        this.loaded = true;
      })
      .catch(error => {
        alert(error.response.data)
      })
    }
  }
}
</script>

<style>
  .small {
    max-width: 600px;
    margin:  150px auto;
  }
</style>