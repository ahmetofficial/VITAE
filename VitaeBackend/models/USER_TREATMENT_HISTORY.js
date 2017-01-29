/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('USER_TREATMENT_HISTORY', {
    user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      primaryKey: true,
      references: {
        model: 'USER_HEALTH_HISTORY',
        key: 'user_id'
      }
    },
    disease_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      references: {
        model: 'USER_HEALTH_HISTORY',
        key: 'disease_id'
      }
    },
    treatment_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      references: {
        model: 'TREATMENTS',
        key: 'treatment_id'
      }
    },
    treatment_start_date: {
      type: DataTypes.DATE,
      allowNull: false
    },
    treatment_sys_reg_date: {
      type: DataTypes.DATE,
      allowNull: false
    },
    treatment_finish_date: {
      type: DataTypes.DATE,
      allowNull: false
    },
    treatment_effect_on_disease: {
      type: DataTypes.INTEGER(11),
      allowNull: true
    }
  }, {
    tableName: 'USER_TREATMENT_HISTORY'
  });
};
