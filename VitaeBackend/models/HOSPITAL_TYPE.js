/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('HOSPITAL_TYPE', {
    hospital_type_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true
    },
    hospital_type: {
      type: DataTypes.STRING,
      allowNull: false
    }
  }, {
    tableName: 'HOSPITAL_TYPE'
  });
};
