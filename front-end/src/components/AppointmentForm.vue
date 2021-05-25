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

        <v-btn color="primary" @click="e1 = 2" :disabled=isComplete> Continue </v-btn>

        <v-btn text @click="dialog"> Cancel </v-btn>
      </v-stepper-content>

      <v-stepper-content step="2">
        <v-row>
          <v-col>
            <v-card class="mx-auto" max-width="300" tile>
              <v-list dense>
                <v-subheader>Medicines</v-subheader>
                <v-list-item-group v-model="selectedItem" color="primary">
                  <v-list-item v-for="(item, i) in items" :key="i">
                    <v-list-item-content>
                      <v-list-item-title
                        v-text="item.medicineDTO.name"
                        @dblclick="addmedicine(item.id)"
                      ></v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                </v-list-item-group>
              </v-list>
            </v-card>
          </v-col>
          <v-col>
            <v-card class="mx-auto" max-width="300" tile>
              <v-list dense>
                <v-subheader>Prescribed medicines</v-subheader>
                <v-list-item-group v-model="selectedItem" color="primary">
                  <v-list-item v-for="(item, i) in prescribed_items" :key="i">
                    <v-list-item-content>
                      <v-list-item-title
                        v-text="item.medicineDTO.name"
                        @dblclick="removemedicine(item.id)"
                      ></v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                </v-list-item-group>
              </v-list>
            </v-card>
          </v-col>
        </v-row>
        <v-row justify="center">
          <label for="example-number-input" class="col-2 col-form-label"
            >Days</label
          >
          <div class="col-10">
            <input
              class="form-control"
              type="number"
              value="1"
              min="1"
              id="example-number-input"
              v-model="days"
            />
          </div>
        </v-row>
          <v-btn color="primary" @click="e1=3" :disabled='!isComplete'> Continue </v-btn>
          <v-btn text @click="dialog"> Cancel </v-btn>
      </v-stepper-content>

      <v-stepper-content id="step3" step="3" style='width:700px'>
        <PharmacistNewAppointment style="margin-bottom:5px"/>

        <v-btn color="primary" @click="onFinish"> Finish </v-btn>

        <v-btn text @click="dialog"> Cancel </v-btn>
      </v-stepper-content>
    </v-stepper-items>
  </v-stepper>
</template>

<script>
import TokenDecoder from "@/services/token-decoder";
import PharmacistNewAppointment from "@/components/PharmacistNewAppointment"
import axios from "axios";
export default {
  name: "AppointmentForm",
  components:{PharmacistNewAppointment},
  data() {
    return {
      e1: 1,
      report: "",
      items: [],
      prescribed_items: [],
      days: 1
    };
  },
  created() {
    let path = "http://localhost:9090/api/medicine/all";
    axios.get(path).then((response) => {
      this.items = response.data;
    });
  },
  computed: {
    isComplete () {
      return (!this.report) || (this.prescribed_items.length>0);
    }
  },
  methods: {
    dialog() {
      this.$emit("clicked");
      this.report = "";
      this.prescribed_items = [];
      this.e1 = 1;
      this.days=1;
    },
    addmedicine(id) {
      let farm = TokenDecoder.getUserEmail();
      let i;
      this.items.forEach((element) => {
        if (element.id == id) i = element;
      });
      let spec =
        "Name: " +
        i.medicineDTO.name +
        "\nType: " +
        i.medicineDTO.type +
        "\nManufacturer: " +
        i.medicineDTO.manufacturer +
        "\nDescription: " +
        i.medicineDTO.description;
      alert(spec);
      axios
        .get(`http://localhost:9090/api/medicine/check/${farm}/${id}`)
        .then((response) => {
          console.log(response.data);
          if (response.data.length != 0) {
            var found = false;
            for (var x = 0; x < this.prescribed_items.length; x++) {
              if (this.prescribed_items[x].id == response.data[0].id) {
                found = true;
                break;
              }
            }
            if (!found) this.prescribed_items.push(response.data[0]);
          }
        });
    },
    removemedicine(id) {
      for (var i = 0; i < this.prescribed_items.length; i++) {
        if (this.prescribed_items[i].id == id) {
          this.prescribed_items.splice(i, 1);
          break;
        }
      }
    },
    onFinish(){
      this.$emit("clicked");
      this.report = "";
      this.prescribed_items = [];
      this.e1 = 1;
      this.days=1;
    }
  },
};
</script>

<style scoped>
#step3{
  padding: 1px;
}
</style>