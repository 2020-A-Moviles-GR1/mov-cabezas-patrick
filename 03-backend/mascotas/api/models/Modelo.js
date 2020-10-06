/**
 * Modelo.js
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
    anio_lanzamiento:{
      type:"number",
      required: true
    },
    precio: {
      type:"number",
      required: true
    },
    disponible: {
      type:"boolean",
      required: true
    },
    latitud: {
      type:"string"
    },
    longitud: {
      type:"string"
    },
    url: {
      type:"string"
    },
    url_img: {
      type:"string"
    },
    marca: { // Many to One (nombre FK) - mismo nombre q la relacion
      model: 'marca',
      required: true
      // required: true // (Es opcional 1 muchos 0 muchos)
    } 
  },

};

