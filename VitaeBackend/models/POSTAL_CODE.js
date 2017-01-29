/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('POSTAL_CODE', {
    postal_code_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true
    },
    code: {
      type: DataTypes.INTEGER(11),
      allowNull: false
    }
  }, {
    tableName: 'POSTAL_CODE'
  });
};
