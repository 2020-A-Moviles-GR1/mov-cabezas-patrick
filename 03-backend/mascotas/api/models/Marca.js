/**
 * Marca.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
   nombre: {
      type:"string",
      unique: true,
      required: true
    },
    pais_origen:{
      type:"string",
      required: true
    },
    anio_creacion: {
      type:"number",
      required: true
    },
    sucursal_local: {
      type:"boolean",
      required: true
    },
    valor: {
      type:"number",
      required: true
    },
    modelo: { // One to Many (plural)
      collection: 'modelo', // Referencia al modelo
      via: 'marca' // Nombre Foreign Key en 'Pokemon'
    }
  },
};
