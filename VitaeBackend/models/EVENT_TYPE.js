/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('EVENT_TYPE', {
    event_type_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true
    },
    event_type_name: {
      type: DataTypes.STRING,
      allowNull: true
    }
  }, {
    tableName: 'EVENT_TYPE'
  });
};
