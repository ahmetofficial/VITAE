/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('DISTRICT', {
    distict_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true
    },
    district_name: {
      type: DataTypes.STRING,
      allowNull: false
    },
    province_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'CITIES',
        key: 'city_id'
      }
    },
    postal_code_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'POSTAL_CODE',
        key: 'postal_code_id'
      }
    }
  }, {
    tableName: 'DISTRICT'
  });
};
