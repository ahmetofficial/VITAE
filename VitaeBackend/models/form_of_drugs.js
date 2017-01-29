'use strict';
module.exports = function(sequelize, DataTypes) {
  var FORM_OF_DRUGS = sequelize.define('FORM_OF_DRUGS', {
      form_id: {
          type: DataTypes.INTEGER(11),
          allowNull: false,
          primaryKey: true
      },
      form_name: {
          type: DataTypes.STRING,
          allowNull: false
      },
      form_description: {
          type: DataTypes.STRING,
          allowNull: false
      }

  }, {
    classMethods: {
      associate: function(models) {
        // associations can be defined here
      }
    }
  });
  return FORM_OF_DRUGS;
};