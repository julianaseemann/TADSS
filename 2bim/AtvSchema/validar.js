const Ajv2020 = require("ajv/dist/2020");
const addFormats = require("ajv-formats");
const fs = require("fs");

const schema = JSON.parse(
  fs.readFileSync("./PedidoSchema.json", "utf8")
);

const data = JSON.parse(
  fs.readFileSync("./Pedido.json", "utf8")
);

const ajv = new Ajv2020();
addFormats(ajv);

const validate = ajv.compile(schema);

if (validate(data)) {
  console.log("JSON válido!");
} else {
  console.log("JSON inválido!");
  console.log(validate.errors);
}