/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('COUNTRIES', {
    country_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true
    },
    country_name: {
      type: DataTypes.STRING,
      allowNull: false
    },
    country_code: {
      type: DataTypes.STRING,
      allowNull: false
    },
    country_phone_code: {
      type: DataTypes.INTEGER(11),
      allowNull: false
    },
    has_states: {
      type: DataTypes.INTEGER(1),
      allowNull: false
    }
  }, {
    tableName: 'COUNTRIES'
  });
};
