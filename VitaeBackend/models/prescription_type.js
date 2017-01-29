'use strict';
module.exports = function(sequelize, DataTypes) {
  var PRESCRIPTION_TYPE = sequelize.define('PRESCRIPTION_TYPE', {
    prescription_type_id: DataTypes.STRING
  }, {
    classMethods: {
      associate: function(models) {
        // associations can be defined here
      }
    }
  });
  return PRESCRIPTION_TYPE;
};