/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('INSTITUTE_TYPES', {
    institute_type_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true
    },
    institute_type_name: {
      type: DataTypes.STRING,
      allowNull: false
    }
  }, {
    tableName: 'INSTITUTE_TYPES'
  });
};
