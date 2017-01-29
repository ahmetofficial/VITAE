/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('TREATMENT_GAP_PERIODS', {
    user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USER_TREATMENT_HISTORY',
        key: 'user_id'
      }
    },
    disease_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'USER_TREATMENT_HISTORY',
        key: 'disease_id'
      }
    },
    treatment_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'USER_TREATMENT_HISTORY',
        key: 'treatment_id'
      }
    },
    gap_start_date: {
      type: DataTypes.DATE,
      allowNull: false
    },
    gap_finish_date: {
      type: DataTypes.DATE,
      allowNull: true
    }
  }, {
    tableName: 'TREATMENT_GAP_PERIODS'
  });
};
