/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('DISEASES', {
    disease_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      autoIncrement: true
    },
    disease_name: {
      type: DataTypes.STRING,
      allowNull: false
    },
    disease_latin_name: {
      type: DataTypes.STRING,
      allowNull: true
    },
    disease_reason_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'DISEASE_PRIOR_REASON',
        key: 'disease_reason_id'
      }
    },
    incubation_period_in_days: {
      type: DataTypes.INTEGER(11),
      allowNull: true
    },
    is_chronic: {
      type: DataTypes.INTEGER(1),
      allowNull: false
    },
    gender_factor: {
      type: DataTypes.STRING,
      allowNull: false
    },
    rate_of_appearance: {
      type: "DOUBLE(10,5)",
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
    }
  }, {
    tableName: 'DISEASES'
  });
};
