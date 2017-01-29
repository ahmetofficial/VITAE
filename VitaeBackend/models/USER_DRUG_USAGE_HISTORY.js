/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('USER_DRUG_USAGE_HISTORY', {
    user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      primaryKey: true,
      references: {
        model: 'USER_TREATMENT_HISTORY',
        key: 'user_id'
      }
    },
    disease_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      references: {
        model: 'USER_TREATMENT_HISTORY',
        key: 'disease_id'
      }
    },
    treatment_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      references: {
        model: 'USER_TREATMENT_HISTORY',
        key: 'treatment_id'
      }
    },
    drug_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      references: {
        model: 'DRUGS',
        key: 'drug_id'
      }
    },
    drug_usage_start_date: {
      type: DataTypes.DATE,
      allowNull: false
    },
    drug_usage_sys_reg_date: {
      type: DataTypes.DATE,
      allowNull: false
    },
    drug_usage_finish_date: {
      type: DataTypes.DATE,
      allowNull: true
    },
    drug_effect_on_disease: {
      type: DataTypes.INTEGER(11),
      allowNull: false
    }
  }, {
    tableName: 'USER_DRUG_USAGE_HISTORY'
  });
};
