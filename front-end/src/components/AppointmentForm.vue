<template>
  <v-stepper v-model="e1">
    <v-stepper-header>
      <v-stepper-step :complete="e1 > 1" step="1"> Report </v-stepper-step>

      <v-divider></v-divider>

      <v-stepper-step :complete="e1 > 2" step="2"> Prescribing </v-stepper-step>

      <v-divider></v-divider>

      <v-stepper-step step="3"> New appointment </v-stepper-step>
    </v-stepper-header>

    <v-stepper-items>
      <v-stepper-content step="1">
        <v-textarea
          solo
          name="input-7-4"
          label="Enter report"
          v-model="report"
        ></v-textarea>

        <v-btn color="primary" @click="e1 = 2"> Continue </v-btn>

        <v-btn text @click="dialog"> Cancel </v-btn>
      </v-stepper-content>

      <v-stepper-content step="2">
        <v-card class="mx-auto" max-width="300" tile>
          <v-list dense>
            <v-subheader>Medicines</v-subheader>
            <v-list-item-group v-model="selectedItem" color="primary">
              <v-list-item v-for="(item, i) in items" :key="i">
                <v-list-item-content>
                  <v-list-item-title
                    v-text="item.medicineDTO.name" @dblclick="addmedicine()"
                  ></v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list-item-group>
          </v-list>
        </v-card>
        

        <v-btn color="primary" @click="e1 = 3"> Continue </v-btn>

        <v-btn text> Cancel </v-btn>
      </v-stepper-content>

      <v-stepper-content step="3">
        <v-card class="mb-12" color="grey lighten-1" height="200px"></v-card>

        <v-btn color="primary" @click="e1 = 1"> Continue </v-btn>

        <v-btn text> Cancel </v-btn>
      </v-stepper-content>
    </v-stepper-items>
  </v-stepper>
</template>

<script>
import axios from "axios";
export default {
  name: "AppointmentForm",
  data() {
    return {
      e1: 1,
      report: "",
      items: [],
      prescribed_items: []
    };
  },
  created() {
    let path = "http://localhost:9090/api/medicine/all";
    axios.get(path).then((response) => {
      this.items = response.data;
    });
  },
  methods: {
    dialog() {
      this.$emit("clicked");
    },
  },
};
</script>