/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('DOCTOR_TYPES', {
    doctor_type_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true
    },
    doctor_type: {
      type: DataTypes.STRING,
      allowNull: false
    }
  }, {
    tableName: 'DOCTOR_TYPES'
  });
};
