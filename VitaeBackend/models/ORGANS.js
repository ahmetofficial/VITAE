/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('ORGANS', {
    organ_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      autoIncrement: true
    },
    organ_name: {
      type: DataTypes.STRING,
      allowNull: false
    },
    body_system_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'BODY_SYSTEMS',
        key: 'body_system_id'
      }
    }
  }, {
    tableName: 'ORGANS'
  });
};
