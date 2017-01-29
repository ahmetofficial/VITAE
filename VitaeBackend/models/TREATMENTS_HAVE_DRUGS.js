/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('TREATMENTS_HAVE_DRUGS', {
    drug_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'DRUGS',
        key: 'drug_id'
      }
    },
    treatment_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'TREATMENTS',
        key: 'treatment_id'
      }
    }
  }, {
    tableName: 'TREATMENTS_HAVE_DRUGS'
  });
};
