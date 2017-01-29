/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('TREATMENTS', {
    treatment_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      autoIncrement: true
    },
    treatment_name: {
      type: DataTypes.STRING,
      allowNull: false
    },
    age_range: {
      type: DataTypes.STRING,
      allowNull: true
    },
    avarage_period_in_days: {
      type: DataTypes.INTEGER(11),
      allowNull: true
    },
    body_system_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'BODY_SYSTEMS',
        key: 'body_system_id'
      }
    },
    organ_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'ORGANS',
        key: 'organ_id'
      }
    },
    avarage_succes_rate: {
      type: "DOUBLE(10,5)",
      allowNull: true
    }
  }, {
    tableName: 'TREATMENTS'
  });
};
