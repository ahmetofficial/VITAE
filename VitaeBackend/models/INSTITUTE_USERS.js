/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('INSTITUTE_USERS', {
    user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      primaryKey: true,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    },
    institute_type_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'INSTITUTE_TYPES',
        key: 'institute_type_id'
      }
    },
    focus_area_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'INSTITUTES_FOCUS_AREA',
        key: 'focus_area_id'
      }
    },
    establishment_date: {
      type: DataTypes.DATE,
      allowNull: false
    },
    website: {
      type: DataTypes.STRING,
      allowNull: false
    }
  }, {
    tableName: 'INSTITUTE_USERS'
  });
};
