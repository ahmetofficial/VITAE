/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('DRUG_DOSE_AMOUNT_HISTORY', {
    user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USER_DRUG_USAGE_HISTORY',
        key: 'user_id'
      }
    },
    disease_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'USER_DRUG_USAGE_HISTORY',
        key: 'disease_id'
      }
    },
    treatment_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'USER_DRUG_USAGE_HISTORY',
        key: 'treatment_id'
      }
    },
    drug_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'USER_DRUG_USAGE_HISTORY',
        key: 'drug_id'
      }
    },
    drug_dose_in_miligrams: {
      type: DataTypes.INTEGER(11),
      allowNull: false
    },
    drug_dose_start_date: {
      type: DataTypes.DATE,
      allowNull: false
    },
    drug_dose_finish_date: {
      type: DataTypes.DATE,
      allowNull: true
    }
  }, {
    tableName: 'DRUG_DOSE_AMOUNT_HISTORY'
  });
};
