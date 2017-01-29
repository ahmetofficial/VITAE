/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('DISEASE_LEVEL', {
    disease_level_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true
    },
    disease_level_name: {
      type: DataTypes.STRING,
      allowNull: false
    }
  }, {
    tableName: 'DISEASE_LEVEL'
  });
};
