/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('INSTITUTES_FOCUS_AREA', {
    focus_area_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true
    },
    focus_area: {
      type: DataTypes.INTEGER(11),
      allowNull: false
    },
    subject_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false
    }
  }, {
    tableName: 'INSTITUTES_FOCUS_AREA'
  });
};
